package loaiza.andres.it.coderdojo_firenze.CustomControls.Twitter;

import twitter4j.Status;

public class TwitterListElement {
    private Status post;

    public TwitterListElement(Status post) {
        this.post = post;
    }

    public Status getPost() {
        return post;
    }
}
