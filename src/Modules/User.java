package Modules;

public class User {
    private String nickname;
    private String specialization;
    private String country;

    public User(String nickname, String specialization, String country){
        this.country = country;
        this.nickname = nickname;
        this.specialization = specialization;
    }

    public User(String nickname, String specialization){
        this.specialization = specialization;
        this.nickname = nickname;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getCountry() {
        return country;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSpecialization() {
        return specialization;
    }
}
