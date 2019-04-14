package local.jordi.kwetter.service;

import local.jordi.kwetter.domain.Tweet;

public interface ITweetService extends IService<Tweet>
{
    void SendReaction(Tweet tweet, Tweet reaction);
}
