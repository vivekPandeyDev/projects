package com.example.soapfileservice.endpoints;

import com.example.soapfileservice.dto.*;
import com.example.soapfileservice.service.FileDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import java.io.IOException;

@Endpoint
@Component
public class FileServiceEndpoint {
    private static final String NAMESPACE_URI = "http://docs.oasis-open.org/ns/cmis/messaging/200908/";

    private static final Logger logger = LoggerFactory.getLogger(FileServiceEndpoint.class);

    private final FileDocumentService fileDocumentService;

    @Autowired
    public FileServiceEndpoint(FileDocumentService fileDocumentService) {
        this.fileDocumentService = fileDocumentService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addContentStreamRequest")
    @ResponsePayload
    public GetObjectIdResponse createDocument(@RequestPayload AddContentStreamRequest request) {

        String objectId = fileDocumentService.storeFileAndGetObjectId(request);
        logger.info("generated object id: {}",objectId);
        // return response
        GetObjectIdResponse getObjectIdResponse=  new GetObjectIdResponse();
        getObjectIdResponse.setId(objectId);
        return getObjectIdResponse;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDocumentRequest")
    @ResponsePayload
    public GetDocumentResponse getImageResponse(@RequestPayload GetDocumentRequest request) throws IOException {
        GetDocumentResponse getDocumentResponse = new GetDocumentResponse();
        getDocumentResponse.setImage(fileDocumentService.getImage(request.getObjectId()));
        return getDocumentResponse;
    }
}
