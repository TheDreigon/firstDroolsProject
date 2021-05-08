package droolscours;

@SuppressWarnings("javadoc")
public class PrivateAccount extends Account {

    private Customer owner;

    public Customer getOwner() {
        return this.owner;
    }

    public void setOwner(final Customer owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        final StringBuffer buff = new StringBuffer();
        buff.append("-----Private Account-)");
        buff.append(super.toString());
        if (this.owner != null) {
            buff.append(this.owner.toString());
        }
        buff.append("-----Private Account end-)");
        return buff.toString();
    }
}