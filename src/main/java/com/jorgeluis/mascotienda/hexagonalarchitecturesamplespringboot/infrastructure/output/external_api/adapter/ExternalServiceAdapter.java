package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.external_api.adapter;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.task.AdditionalTaskInfo;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.ExternalServicePort;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.external_api.model.JsonPlaceholderTodo;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.external_api.model.JsonPlaceholderUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalServiceAdapter implements ExternalServicePort {

    private final RestTemplate restTemplate;

    public ExternalServiceAdapter() {
        restTemplate = new RestTemplate();
    }

    @Override
    public AdditionalTaskInfo getAdditionalTaskInfo(Long taskId) {
        String apiUrl = "https://jsonplaceholder.typicode.com/todos/" + taskId;
        ResponseEntity<JsonPlaceholderTodo> response = restTemplate.getForEntity(apiUrl, JsonPlaceholderTodo.class);
        JsonPlaceholderTodo todo = response.getBody();

        if (todo == null) {
            return null;
        }

        apiUrl = "https://jsonplaceholder.typicode.com/users/" + todo.getUserId();
        ResponseEntity<JsonPlaceholderUser> userResponse = restTemplate.getForEntity(apiUrl, JsonPlaceholderUser.class);
        JsonPlaceholderUser user = userResponse.getBody();

        if (user == null) {
            return null;
        }

        return new AdditionalTaskInfo(user.getId(), user.getName(), user.getEmail());
    }

}
