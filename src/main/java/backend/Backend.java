
/**
 *date: 25.11.2018   -  time: 20:04:27
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package backend;

import presenter.InstitutionPresenter;
import presenter.InstitutionPresenterAdmin;

public class Backend {

	private static class MyBackend {
		final static InstitutionPresenter INSTITUTIONPRESENTER = new InstitutionPresenter();
		final static InstitutionPresenterAdmin INSTITUTIONPRESENTERADMIN = new InstitutionPresenterAdmin();

		private MyBackend() {
		}

		
	}

	public Backend() {
	}

	public static InstitutionPresenter getInstitutionPresenterInstance() {
		return MyBackend.INSTITUTIONPRESENTER;
	}

	public static InstitutionPresenterAdmin getInstitutionPresenterAdminInstance() {
		return MyBackend.INSTITUTIONPRESENTERADMIN;
	}

}
