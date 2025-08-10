# EmailSender
This project uses the SendGrid API to send email messages.
#How project works:
I have a database,here there is a "User" table and there is id,email,securityCode in this table.
I have a "email/send" api.This method receives an email,finds according securityCode of this email and sends this securityCode of this email to this email.
We can change this "email/send" method easily if we want to send a lot of messages to emails automaticly.

#You need:
sendGrid ApiKey
mail ( may be corporative to seem professional)
database (if you want to read emails from file and to send message to emails(modifiying this method),you don't need database.
