package local.jordi.kwetter.service;

public interface IAuthenticateService
{
    long authenticate(String username, String password);
}
