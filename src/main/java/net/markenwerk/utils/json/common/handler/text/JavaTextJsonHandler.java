/*
 * Copyright (c) 2016 Torsten Krause, Markenwerk GmbH
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.markenwerk.utils.json.common.handler.text;

import net.markenwerk.utils.text.indentation.Indentation;

/**
 * A {@link JavaTextJsonHandler} is a
 * {@link AbstractAppendingJavaTextJsonHandler} that calculates a text, that
 * mimics the {@link Object#toString()} behavior of Javas collection classes,
 * for the handled JSON document as a result.
 * 
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public final class JavaTextJsonHandler extends AbstractAppendingJavaTextJsonHandler<StringBuilder, String> {

	/**
	 * Creates a new {@link JavaTextJsonHandler}.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given {@link Appendable} is {@literal null} or if the
	 *             given {@link Indentation} is {@literal null}.
	 */
	public JavaTextJsonHandler() {
		super(new StringBuilder());
	}

	@Override
	public String getResult() {
		return getAppendable().toString();
	}

}