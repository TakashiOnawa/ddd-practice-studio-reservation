package org.taonaw.facilitymanagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.facilitymanagement.application.change_studio.ChangeStudioAppService;
import org.taonaw.facilitymanagement.application.change_studio.ChangeStudioCommand;
import org.taonaw.facilitymanagement.application.change_studio.ChangeStudioResult;
import org.taonaw.facilitymanagement.application.register_studio.RegisterStudioAppService;
import org.taonaw.facilitymanagement.application.register_studio.RegisterStudioCommand;
import org.taonaw.facilitymanagement.query.studio.IStudioQuery;
import org.taonaw.facilitymanagement.query.studio.StudioDto;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudioController {
    @Autowired
    private final RegisterStudioAppService registerStudioAppService;
    @Autowired
    private final ChangeStudioAppService changeStudioAppService;
    @Autowired
    private final IStudioQuery studioQuery;

    @PostMapping("/studios")
    public ResponseEntity<Void> registerStudio(
            @RequestBody RegisterStudioCommand request,
            UriComponentsBuilder uriComponentsBuilder) {
        var response = registerStudioAppService.handle(request);
        var uri = uriComponentsBuilder.path("/studios/{studioId}")
                .buildAndExpand(response.getStudioId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/studios/{studioId}")
    public ResponseEntity<ChangeStudioResult> changeStudio(
            @PathVariable String studioId,
            @RequestBody ChangeStudioCommand request) {
        var response = changeStudioAppService.handle(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/studios")
    public ResponseEntity<List<StudioDto>> getStudios() {
        var studios = studioQuery.getAll();
        return ResponseEntity.ok(studios);
    }
}
