package gr.wind.spectra.business;

import java.security.SecureRandom;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Password
{
	// The higher the number of iterations the more
	// expensive computing the hash is for us and
	// also for an attacker.
	private final int iterations = 3;
	private final int saltLen = 32;
	private final int desiredKeyLen = 256;

	/**
	 * Computes a salted PBKDF2 hash of given plaintext password suitable for
	 * storing in a database. Empty passwords are not supported.
	 */
	public String getSaltedHash(String password) throws Exception
	{
		byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
		// store the salt with the password
		return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
	}

	/**
	 * Checks whether given plaintext password corresponds to a stored salted hash
	 * of the password.
	 */
	public boolean check(String password, String stored) throws Exception
	{
		String[] saltAndHash = stored.split("\\$");
		if (saltAndHash.length != 2)
		{
			throw new IllegalStateException("The stored password must have the form 'salt$hash'");
		}
		String hashOfInput = hash(password, Base64.decodeBase64(saltAndHash[0]));
		return hashOfInput.equals(saltAndHash[1]);
	}

	// using PBKDF2 from Sun, an alternative is https://github.com/wg/scrypt
	// cf. http://www.unlimitednovelty.com/2012/03/dont-use-bcrypt.html
	private String hash(String password, byte[] salt) throws Exception
	{
		if (password == null || password.length() == 0)
			throw new IllegalArgumentException("Empty passwords are not supported.");
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		SecretKey key = f.generateSecret(new PBEKeySpec(password.toCharArray(), salt, iterations, desiredKeyLen));
		return Base64.encodeBase64String(key.getEncoded());
	}

	public byte[] hexStringToByteArray(String s)
	{
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2)
		{
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public void main(String[] args) throws Exception
	{
		// String getSaltedHash(String password);
		// boolean checkPassword(String password, String stored);

		// System.out.println(Password.getSaltedHash("1234")); //
		// Lw9HiROpB27ORsrAC3ZgERejYO7XQERVyXTCnU9aqVw=$EkWO1U+82gyO/KmMpSZG19A45XmzApEYA+8wcCFs+F4=
		// J+l1pMaP6LeCgk9usune5QrL0oFRHpOel4H7DXPK5jc=$ugw0RoIXLH5uOnOGhPoQwU2VOdm52VEGUsNlKAkjWsE=
		// System.out.println(Password.check("1234",
		// "0AEPAfgSNcl0ek4APoDj+7YnXB1AYLwIt/BnCGW9/9A=$CltC0npEjplLnkGvVvpnSaf4qyaYv/gZgn+AE3FZIaA="));

		// System.out.println(check("12134",
		// "Lw9HiROpB27ORsrAC3ZgERejYO7XQERVyXTCnU9aqVw=$EkWO1U+82gyO/KmMpSZG19A45XmzApEYA+8wcCFs+F4="));
	}

}