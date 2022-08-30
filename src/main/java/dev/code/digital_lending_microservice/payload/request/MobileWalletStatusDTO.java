package dev.code.digital_lending_microservice.payload.request;

import dev.code.digital_lending_microservice.domain.MobileWallet;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 30/08/2022
 */


public record MobileWalletStatusDTO(MobileWallet mobileWallet, String transactionStatus) {

}
