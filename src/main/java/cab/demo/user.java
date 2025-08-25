package cab.demo;

import jakarta.persistence.*;
import lombok.*;

    @Entity @Table(name="users")
    @Data @NoArgsConstructor @AllArgsConstructor
    public class user {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @Column(unique = true)
        private String email;

        private String password;

        private String role;

        public boolean isPresent() {
            return this.id!=null;
        }

    }
