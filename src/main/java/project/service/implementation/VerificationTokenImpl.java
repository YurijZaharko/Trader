package project.service.implementation;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import project.service.VerificationTokenService;

import java.util.Arrays;

@Service
public class VerificationTokenImpl implements VerificationTokenService {
    @Override
    public String verificationKeyGenerator(String email, String nickName, String currentDate) {
        String data = email + currentDate + nickName;
        byte[] temp = Base64.encodeBase64(DigestUtils.sha256(data));
        String string = Arrays.toString(temp);
        return string.replaceAll(", ", "");
    }
}
