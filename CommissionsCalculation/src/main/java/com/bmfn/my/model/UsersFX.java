package com.bmfn.my.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mt4_users", schema = "report")
public class UsersFX extends Users {
}
