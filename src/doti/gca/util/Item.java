/*
 * Demoiselle Framework
 * Copyright (C) 2010 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 * 
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo Ã© parte do Framework Demoiselle.
 * 
 * O Framework Demoiselle Ã© um software livre; vocÃª pode redistribuÃ­-lo e/ou
 * modificÃ¡-lo dentro dos termos da GNU LGPL versÃ£o 3 como publicada pela FundaÃ§Ã£o
 * do Software Livre (FSF).
 * 
 * Este programa Ã© distribuÃ­do na esperanÃ§a que possa ser Ãºtil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implÃ­cita de ADEQUAÃ‡ÃƒO a qualquer MERCADO ou
 * APLICAÃ‡ÃƒO EM PARTICULAR. Veja a LicenÃ§a PÃºblica Geral GNU/LGPL em portuguÃªs
 * para maiores detalhes.
 * 
 * VocÃª deve ter recebido uma cÃ³pia da GNU LGPL versÃ£o 3, sob o tÃ­tulo
 * "LICENCA.txt", junto com esse programa. Se nÃ£o, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a FundaÃ§Ã£o do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */

package doti.gca.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {

	private final String alias;
	private final String subject;
	private final String initDate;
	private final String endDate;
	private final String issuer;

	public Item(String alias, String subject, Date initDate, Date endDate, String issuer) {
		DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		this.alias = alias;
		this.subject = this.corte(subject);
		this.initDate = f.format(initDate);
		this.endDate = f.format(endDate);
		this.issuer = this.corte(issuer);
	}

	public String getAlias() {
		return alias;
	}

	public String getSubject() {
		return subject;
	}

	public String getInitDate() {
		return initDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getIssuer() {
		return issuer;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(subject);
		return buffer.toString();
	}

	private String corte(String texto) {

		int end = 0;
		end = texto.indexOf(",");
		if (end == -1)
			end = texto.length();

		String cortado = texto.substring(0, end);
		return cortado.replace("CN=", "");
	}
}
