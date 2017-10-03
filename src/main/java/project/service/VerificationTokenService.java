package project.service;

public interface VerificationTokenService {

    String verificationKeyGenerator(String email, String nickName, String currentDate);
}
