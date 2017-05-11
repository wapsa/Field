import javax.crypto.*;
import java.security.*;

/*
 	Performs encryption and decryption algorithm
 	
 */
public class key
	{
	
	/* Stores a generated  key */

	private Key key;
	
	/*
	 	Gets the Key used for the encryption
	 	return Key generated,or null if none generated
	 */

	public Key getKey()
	{
		return key;
	}
	
	/*
		Performs encryption using DES algorithm
	 	@param data	The data to be encrypted
	 	@return The encrypted data, or null if enryption fails
	 */

	public byte[] encrypt(byte[] data)
	{
		key = null;
		try
		{
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			KeyGenerator kg = KeyGenerator.getInstance("DES");
			Cipher cipher = Cipher.getInstance("DES");

			key = kg.generateKey();
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return (cipher.doFinal(data));
		}
		catch(Exception e){return null;}
	}
	
	/*
	 	Performs decryption using DES algorithm and a Key
	 	
	 */

	public byte[] decrypt(byte[] data, Key key)
	{
		try
		{
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			KeyGenerator kg = KeyGenerator.getInstance("DES");
			Cipher cipher = Cipher.getInstance("DES");

			cipher.init(Cipher.DECRYPT_MODE, key);
			return(cipher.doFinal(data));
		}

		catch(Exception e)
		{
		  return null;
		}
	}		
}
 
