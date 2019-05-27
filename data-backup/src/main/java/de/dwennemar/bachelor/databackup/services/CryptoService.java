package de.dwennemar.bachelor.databackup.services;

import de.dwennemar.bachelor.databackup.exception.CryptoServiceFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class CryptoService {
    private static final String AES = "AES";
    private final Logger log = LoggerFactory.getLogger(CryptoService.class);

    public String encrypt(String value, String key) throws CryptoServiceFailedException {
        return encrypt(value.getBytes(), key.getBytes());
    }


    public String encrypt(byte[] value, byte[] key) throws CryptoServiceFailedException {
        String result;
        try {
            byte[] res = getCipher(key).doFinal(value);
            result = Base64.getEncoder().encodeToString(res);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException e) {
            log.error(e.toString());
            throw new CryptoServiceFailedException();
        }

        return result;
    }

    public String decrypt(String value, String key) throws CryptoServiceFailedException {
        return decrypt(value, key.getBytes());
    }

    public String decrypt(String value, byte[] key) throws CryptoServiceFailedException {
        String result;

        try {
            byte[] val = Base64.getDecoder().decode(value);
            byte[] res = getCipher(key, Cipher.DECRYPT_MODE).doFinal(val);

            result = new String(res);

        } catch (BadPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException e) {
            log.error(e.toString());
            throw new CryptoServiceFailedException();
        }

        return result;
    }

    public Cipher getCipher(byte[] key) throws NoSuchAlgorithmException {
        return getCipher(key, Cipher.ENCRYPT_MODE);
    }

    public Cipher getCipher(byte[] key, int mode) throws NoSuchAlgorithmException {
        Cipher c;
        try {
            c = Cipher.getInstance(AES);
            SecretKeySpec keySpec = new SecretKeySpec(key, AES);
            c.init(mode, keySpec);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            log.error(e.toString());
            throw new NoSuchAlgorithmException();
        }
        return c;
    }

}
