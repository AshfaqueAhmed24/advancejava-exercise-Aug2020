/*
 * #%L
 * Advanced Java LIVE course-2020
 * %%
 * Copyright (C) 2020 MasterDevSkills.com
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

package com.masterdevskills.cha1.ext1;

import java.time.Instant;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 04 August 2020
 */
public class LambdaExpression2 {
	private static final Logger logger = Logger.getLogger(LambdaExpression2.class.getName());

	/**
	 * TODO Create a functional interface called Executable
	 * Add a method called execute()
	 * it doesn't take anything and returns void
	 * use this functional interface as argument of the following method and log
	 * the time it takes to execute the method
	 */
	public void executionTime(Executable executable) {
		long startTime = System.currentTimeMillis();
		executable.execute();
		long endTime = System.currentTimeMillis();

		logger.info("Execution time : " + (endTime - startTime) + "ms");
	}

	/* TODO: use the above of method here           --->// above method
	 */
	public void run() {
		Executable executable = () -> {
			IntStream.range(0, 5_000_0000).boxed().forEach(integer -> {
				var logInteger = Math.log((double) integer);
			});
		};

		executionTime(executable);
	}

	@FunctionalInterface
	public interface Executable {
		void execute();
	}
}
