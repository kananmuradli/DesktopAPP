package az.javafx.model;

import az.javafx.exceptions.*;

public class Person {

    private Long id;
    private String name;
    private String surname;
    private String DOB;
    private String seriaNum;
    private boolean active;
    private String phone;
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String email;

   public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
           if (DOB == "null") {
               throw new DOBException("Doğum tarixinizi daxil edin");
           } else if (DOB.length() != 0) {
               this.DOB = DOB.trim();
           } else {
               throw new DOBException("Doğum tarixinizi daxil edin");
           }
       }

    public String getPhone() {
        if(phone== null) {
            return "Məlumat yoxdur";
        }
        return phone;
    }

    public void setPhone(String phone) {
        if (phone.length() !=0){
            this.phone = phone.trim();
        }else{
          throw new PhoneException("Əlaqə nömrənizi daxil edin");
        }
    }

    public String getEmail() {
        if(email== null) {
            return "Məlumat yoxdur";
        }
        return email;
    }

    public void setEmail(String email) {
        if (email.length() !=0){
            this.email = email.trim();
        }else{
         throw new EmailException("Emailinizi daxil edin");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
       if (name.length() !=0){
           this.name = name.trim();
       }else{
        throw new NameException("Adınızı daxil edin");
       }
   }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname.length() !=0) {
            this.surname = surname.trim();
        } else {
        throw  new SurnameException("Soyadınızı daxil edin");
        }
    }

    public String getSeriaNum() {

        return seriaNum;
    }

    public void setSeriaNum(String seriaNum) {
        if (seriaNum.length() !=0) {
            if (seriaNum.length() == 7) {
                this.seriaNum = seriaNum.trim();
            } else {
                throw new SeriaNumException("7 simvol olmalıdır");
            }
            }else{
                throw new SeriaNumException("Seria nömrəsini daxil edin");
            }
        }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
