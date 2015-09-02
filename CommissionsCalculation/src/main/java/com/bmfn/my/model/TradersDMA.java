package com.bmfn.my.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "mt4_trades", schema = "report_au")
public class TradersDMA extends Traders {
}
