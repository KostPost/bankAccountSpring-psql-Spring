package com.kostpost.banksystemspringpsql.bankData;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("admin")
public class AdminAccount extends Account {

    @Column(name = "admin_level")
    private long level;



}
