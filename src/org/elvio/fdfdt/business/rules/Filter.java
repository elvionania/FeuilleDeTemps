package org.elvio.fdfdt.business.rules;

import org.elvio.fdfdt.file.conf.meta.Information;

public interface Filter {

	public abstract Information applyFilter(
			Information configuration);

}