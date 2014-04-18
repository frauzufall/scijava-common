/*
 * #%L
 * SciJava Common shared library for SciJava software.
 * %%
 * Copyright (C) 2009 - 2014 Board of Regents of the University of
 * Wisconsin-Madison, Broad Institute of MIT and Harvard, and Max Planck
 * Institute of Molecular Cell Biology and Genetics.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package org.scijava.platform;

/**
 * Abstract superclass of platform implementations.
 * 
 * @author Curtis Rueden
 */
public abstract class AbstractPlatform implements Platform {

	private PlatformService platformService;

	// -- Platform methods --

	@Override
	public String javaVendor() {
		return null;
	}

	@Override
	public String javaVersion() {
		return null;
	}

	@Override
	public String osArch() {
		return null;
	}

	@Override
	public String osName() {
		return null;
	}

	@Override
	public String osVersion() {
		return null;
	}

	@Override
	public boolean isTarget() {
		if (javaVendor() != null) {
			final String javaVendor = System.getProperty("java.vendor");
			if (!javaVendor.matches(".*" + javaVendor() + ".*")) return false;
		}

		if (javaVersion() != null) {
			final String javaVersion = System.getProperty("java.version");
			if (javaVersion.compareTo(javaVersion()) < 0) return false;
		}

		if (osName() != null) {
			final String osName = System.getProperty("os.name");
			if (!osName.matches(".*" + osName() + ".*")) return false;
		}

		if (osArch() != null) {
			final String osArch = System.getProperty("os.arch");
			if (!osArch.matches(".*" + osArch() + ".*")) return false;
		}

		if (osVersion() != null) {
			final String osVersion = System.getProperty("os.version");
			if (osVersion.compareTo(osVersion()) < 0) return false;
		}

		return true;
	}

	@Override
	public void configure(final PlatformService service) {
		platformService = service;
	}

	@Override
	public boolean registerAppMenus(final Object menus) {
		return false;
	}

	// -- Disposable methods --

	@Override
	public void dispose() {
		// NB: Do nothing by default.
	}

	// -- Internal methods --

	protected PlatformService getPlatformService() {
		return platformService;
	}

}
