aws kms encrypt --key-id ${key_id} --plaintext "Hello" --output text --query CiphertextBlob | base64 --decode > ec.txt
