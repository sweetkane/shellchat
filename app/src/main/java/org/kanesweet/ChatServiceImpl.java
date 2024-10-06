package org.kanesweet;

import io.grpc.stub.StreamObserver;
import org.kanesweet.chat.*;

public class ChatServiceImpl extends ChatServiceGrpc.ChatServiceImplBase {

    @Override
    public void sendChat(
        org.kanesweet.chat.Chat request,
        StreamObserver<StatusResponse> responseObserver
    ) {
        System.out.println(request.getUsername() + ": " + request.getMessage());

        StatusResponse response = StatusResponse.newBuilder()
            .setCode(200)
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
