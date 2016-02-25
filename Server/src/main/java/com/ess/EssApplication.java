package com.ess;

import io.dropwizard.Application;
//import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.db.DataSourceFactory;
//import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.ess.auth.SimpleAuthenticator;
import com.ess.auth.Token;
import com.ess.data.dao.UserDAO;

import com.ess.rest.UserResource;

import org.skife.jdbi.v2.DBI;

public class EssApplication extends Application<EssConfiguration> {

	public static void main(String[] args) throws Exception {
		new EssApplication().run(args);
	}

	@Override
	public String getName() {
		return "DropWizard Startup";
	}

/*	private final HibernateBundle<EssConfiguration> hibernate = new HibernateBundle<EssConfiguration>(
			ProjectEntity.class) {

		public DataSourceFactory getDataSourceFactory(
				EssConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};*/

	@Override
	public void initialize(Bootstrap<EssConfiguration> bootstrap) {
		// bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null,
		// "js"));
		//bootstrap.addBundle(hibernate);
		bootstrap.addBundle(new MigrationsBundle<EssConfiguration>() {
			public DataSourceFactory getDataSourceFactory(
					EssConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
	}

	@Override
	public void run(EssConfiguration configuration, Environment environment)
			throws ClassNotFoundException {
/*		final TaskResource resource = new TaskResource(
				configuration.getTemplate(), configuration.getDefaultName());

		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(
				configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);

		environment.jersey().register(resource);
*/
		// JDBI Data Access
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment,
				configuration.getDataSourceFactory(), "h2");
		final UserDAO dao = jdbi.onDemand(UserDAO.class);
		environment.jersey().register(new UserResource(dao));
		/*environment.jersey().register(new ProtectedResource());*/

		// Hibernate Data Access
		/*final ProjectDAO projectDAO = new ProjectDAO(
				hibernate.getSessionFactory());
		environment.jersey().register(new UserResource(projectDAO));*/

/*		environment.jersey().register(
				new BasicAuthProvider<Token>(new SimpleAuthenticator(),
						"SUPER SECRET STUFF"));*/

		// environment.jersey().register(new OAuthProvider<User>(new
		// OAuthAuthenticator(),
		// "SUPER SECRET STUFF"));
	}
}