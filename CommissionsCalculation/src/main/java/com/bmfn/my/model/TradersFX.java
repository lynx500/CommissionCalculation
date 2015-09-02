package com.bmfn.my.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mt4_trades", schema = "report")
public class TradersFX extends Traders {
}
