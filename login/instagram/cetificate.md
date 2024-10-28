# Used Openssl command
1) update mac os inorder to upgrade openssl

##### commands:
a) refered [text](https://www.baeldung.com/openssl-self-signed-cert)

/** K : m#p#02@Bbyte0123 */

a) openssl genrsa -des3 -out fav.key 2196
b) openssl req -key fav.key -new -out fav.csr  
c) self signed with date of expiry
openssl x509 -signkey fav.key -in fav.csr -req -days 365 -out fav.crt
d)openssl x509 -text -noout -in fav.crt
above is to print the data of certificate
e) convert to der format
openssl x509 -in fav.crt -outform der -out fav.der  
f) convert to PKCS12
openssl pkcs12 -inkey fav.pem -in fav.crt -export -out fav.pfx
d) covert to p12
openssl pkcs12 -export -in fav.crt -inkey fav.key -out keystore.p12 -name fav -password pass:m#p#02@Bbyte0123


##### query:
1)**is there any encryption apart from genrsa in openssl genrsa -out privatekey.pem 4667?
Yes, there are other encryption algorithms available in OpenSSL besides RSA.**

The genrsa command specifically generates RSA private keys. However, OpenSSL supports a variety of cryptographic algorithms, including:

DSA (Digital Signature Algorithm): Used for digital signatures.
ECDSA (Elliptic Curve Digital Signature Algorithm): A more efficient alternative to DSA, often used in mobile devices and embedded systems.
ECDH (Elliptic Curve Diffie-Hellman): A key exchange protocol used to establish shared secrets.
AES (Advanced Encryption Standard): A symmetric-key encryption algorithm widely used for data encryption.
To generate a private key using a different algorithm, you can use the appropriate OpenSSL command:

DSA: openssl dsaparam -out dsa.pem 2048 (generates DSA parameters) followed by openssl genpkey -algorithm DSA -out dsa_private.pem -paramfile dsa.pem
ECDSA: openssl ecparam -name secp256k1 -out ecdsa.pem (generates ECDSA parameters) followed by openssl genpkey -algorithm EC -out ecdsa_private.pem -paramfile ecdsa.pem
ECDH: openssl ecparam -name secp256k1 -out ecdh.pem (generates ECDH parameters) followed by openssl genpkey -algorithm EC -out ecdh_private.pem -paramfile ecdh.pem
AES: openssl enc -aes-256-cbc -k <password> -out aes.pem (generates an AES key encrypted with a password)
Choosing the right algorithm depends on your specific security needs and the requirements of your application. Factors to consider include the desired level of security, computational efficiency, and compatibility with other systems.