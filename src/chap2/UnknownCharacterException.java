package chap2;

class UnknownCharacterException extends Exception {

  UnknownCharacterException(String unknownInput) {
    super("Unknown character « " + unknownInput + " »");
  }

}