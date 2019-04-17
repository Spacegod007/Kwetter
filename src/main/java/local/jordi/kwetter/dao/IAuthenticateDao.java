package local.jordi.kwetter.dao;

import local.jordi.kwetter.domain.User;

public interface IAuthenticateDao
{
    User getUserByName(String name);
}
