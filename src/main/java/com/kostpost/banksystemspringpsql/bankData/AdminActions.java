//package com.kostpost.banksystemspringpsql.bankData;
//
//
//import lombok.Getter;
//import lombok.Setter;
//
//
//public interface AdminActions extends AdminAccount{
//
//    public void ChangeAdminLevel(AdminAccount adminAccount){
//        if(adminAccount.getLevel() == 3){
//
//        }else{
//            System.out.println("This action available only for admin which level above 2");
//        }
//    }
//
//    public void BanAccount(AdminAccount adminAccount){
//        if(adminAccount.getLevel() >= 2){
//
//        }
//        else{
//            System.out.println("This action available only for admin which level above 1 ");
//        }
//    }
//
//    public void SeeUsers(AdminAccount adminAccount){
//        if(adminAccount.getLevel() >= 2){
//
//        }
//        else{
//            System.out.println("This action available only for admin which level above 1 ");
//        }
//    }
//
//    public void SeeTran(AdminAccount adminAccount){
//        if(adminAccount.getLevel() >= 2){
//
//        }
//        else{
//            System.out.println("This action available only for admin which level above 1 ");
//        }
//    }
//
//    public void SeeAllAccounts(AdminAccount adminAccount){
//
//
//    }
//
//
//}
