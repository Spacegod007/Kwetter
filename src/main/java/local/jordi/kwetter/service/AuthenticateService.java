package local.jordi.kwetter.service;

import local.jordi.kwetter.dao.IAuthenticateDao;
import local.jordi.kwetter.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthenticateService implements IAuthenticateService
{
    @Inject
    private IAuthenticateDao authenticateDao;

    @Override
    public long authenticate(String username, String password)
    {
        User user = authenticateDao.getUserByName(username);
        if (user != null && user.getPassword().equals(password))
        {
            return user.getDomainId();
        }
        throw new IllegalArgumentException("invalid credentials supplied");
    }
}
