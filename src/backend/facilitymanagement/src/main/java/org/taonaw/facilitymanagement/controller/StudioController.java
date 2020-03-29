package org.taonaw.facilitymanagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.facilitymanagement.application.register_studio.RegisterStudioAppService;
import org.taonaw.facilitymanagement.application.register_studio.RegisterStudioRequest;
import org.taonaw.facilitymanagement.application.register_studio.RegisterStudioResponse;

@RestController
@AllArgsConstructor
public class StudioController {
    @Autowired
    private final RegisterStudioAppService registerStudioAppService;

    @PostMapping("/studios")
    public ResponseEntity<RegisterStudioResponse> registerStudio
            (@RequestBody RegisterStudioRequest request, UriComponentsBuilder uriComponentsBuilder) {
        var response = registerStudioAppService.handle(request);
        var uri = uriComponentsBuilder.path("/studios/{studioId}")
                .buildAndExpand(response.getStudioId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
