package bac.schimpelsberger.micronaut.security;

import javax.inject.Singleton;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.http.HttpAttributes;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.filters.AuthenticationFetcher;
import io.reactivex.Flowable;

@Singleton
public class CustomAuthenticationFetcher implements AuthenticationFetcher {

	Logger log =LoggerFactory.getLogger(this.getClass());


	@Override
	public Publisher<Authentication> fetchAuthentication(HttpRequest<?> request) {
		log.info("CustomAuthenticationFetcher was called!");
		Object o=request.getAttribute(HttpAttributes.PRINCIPAL).orElse(null);	
		if (o!=null) {
			Authentication auth=(Authentication)o;
			return Flowable.just(auth);
		}
		return Flowable.empty();
	}

}
