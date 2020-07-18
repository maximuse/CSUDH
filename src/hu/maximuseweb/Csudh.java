package hu.maximuseweb;

class Csudh {
    private String domainName;
    private String ipAddress;

    Csudh(String domainName, String ipAddress) {
        this.domainName = domainName;
        this.ipAddress = ipAddress;
    }

    String getDomainName() {
        return domainName;
    }

    String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String toString() {
        return "Csudh{" +
                "domainName='" + domainName + "'" +
                ", ipAddress='" + ipAddress + "'" +
                "}\n";
    }
}