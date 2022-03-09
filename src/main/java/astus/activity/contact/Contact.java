package astus.activity.contact;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/*
   O script assume que há uma table contact dentro do DB com os campos descritos pela classe Contact
   nesse caso os campos seguintes devem existir:
   UNSIGNED INT INCREMENTAL NOT NULL PK id
   VARCHAR[100] NOT NULL name
   VARCHAR[100] NOT NULL email
   VARCHAR[100] NOT NULL phone
*/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Contact {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotBlank(message = "Name must not be blank")
   private String name;

   @NotBlank(message = "Email must not be blank")
   @Email(message = "Invalid email")
   private String email;

   @NotBlank(message = "Phone must not be blank")
   private String phone;
}
