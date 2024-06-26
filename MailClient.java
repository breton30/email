/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MailClient
{
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private String user;
    private MailItem lastReceivedMail;

    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        this.lastReceivedMail = null;
    }

    /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        MailItem item= server.getNextMailItem(user);
        if(item !=null){
            lastReceivedMail = item;
        }
        return item;
    }

    /**
     * Print the next mail item (if any) for this user to the text 
     * terminal.
     */
    public void printNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if(item == null) {
            System.out.println("No new mail.");
        }
        else {
            item.print();
        }
    }

    /**
     * Send the given message to the given recipient via
     * the attached mail server.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public void sendMailItem(String to,String subject, String message)
    {
        MailItem item = new MailItem(user,to, subject, message);
        server.post(item);
    }
    public int getNumberOfMessageInServer(){
        return server.howManyMailItems(user);
    }
    public void receiveAndAutorespond(){
        MailItem item = getNextMailItem();
        if(item !=null){
            String mensajeAutomatico="Gracias por su mensaje. Le contestare lo antes posible." + " " + item.getMessage();
            String asunto="RE: " + item.getSubject();
            sendMailItem(item.getFrom(), asunto, mensajeAutomatico);
        }
    }
    public String getStatus(){
        return "";
    }
    public MailItem getLastReceivedMail(){
        return lastReceivedMail;
    }
}
