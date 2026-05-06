package br.com.sus.fila_api.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WhatsappService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${zapi.instanceId}")
    private String instanceId;

    @Value("${zapi.token}")
    private String token;

    @Value("${zapi.clientToken}")
    private String clientToken;

    private static final String ZAPI_BASE_URL = "https://api.z-api.io/instances/";

    public void enviarMensagem(String telefone, String mensagem) {
        try {
            String phoneFormatted = formatarTelefone(telefone);

            String url = ZAPI_BASE_URL + instanceId + "/token/" + token + "/send-text";

            Map<String, Object> body = new HashMap<>();
            body.put("phone", phoneFormatted);
            body.put("message", mensagem);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Client-Token", clientToken);   // ← Usando seu token

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("✅ WhatsApp enviado com sucesso!");
            } else {
                System.err.println("❌ Erro Z-API: " + response.getBody());
            }

        } catch (Exception e) {
            System.err.println("⚠️ Erro ao enviar mensagem WhatsApp: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String formatarTelefone(String telefone) {
        String limpo = telefone.replaceAll("[^0-9]", "");
        if (!limpo.startsWith("55")) {
            limpo = "55" + limpo;
        }
        return limpo;
    }
}