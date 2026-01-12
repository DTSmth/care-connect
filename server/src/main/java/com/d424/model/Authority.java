package com.d424.model;

import java.util.Objects;

/**
 * Model class representing an Authority, or a user role within the application.
 *
 * Contains the name of the role. Note that this begins with "ROLE_" in all caps.
 */
public class Authority {

   // Constants representing the Authority objects for the user roles for this application.
   public static final Authority ADMIN_AUTHORITY = new Authority("ROLE_ADMIN");
   public static final Authority USER_AUTHORITY = new Authority("ROLE_USER");

   private String name;

   public Authority() {}

   public Authority(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }


   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Authority authority = (Authority) o;
      return name.equals(authority.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }

   @Override
   public String toString() {
      return "Authority{" +
              "name=" + name +
              '}';
   }
}
