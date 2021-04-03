package Main.cnp;

public interface CnpValidator {
    CnpParts validateCnp(String cnp) throws CnpException;
}
