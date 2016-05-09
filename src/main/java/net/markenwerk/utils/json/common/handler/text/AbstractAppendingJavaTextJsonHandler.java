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

import java.io.IOException;

import net.markenwerk.utils.json.common.JsonException;
import net.markenwerk.utils.json.common.JsonIndexException;
import net.markenwerk.utils.json.common.JsonValueException;
import net.markenwerk.utils.json.handler.IdleJsonHandler;
import net.markenwerk.utils.json.handler.JsonHandler;
import net.markenwerk.utils.json.handler.JsonHandlingException;
import net.markenwerk.utils.text.indentation.Indentation;

/**
 * A {@link AbstractAppendingJavaTextJsonHandler} is a {@link JsonHandler} that
 * appends the handled JSON document as a text, that mimics the
 * {@link Object#toString()} behavior of Javas collection classes, to a given
 * {@link Appendable}.
 * 
 * 
 * @param <ActualAppendable>
 *            The actual {@link Appendable} type.
 * @param <Result>
 *            The result type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 1.0.0
 */
public abstract class AbstractAppendingJavaTextJsonHandler<ActualAppendable extends Appendable, Result> extends
		IdleJsonHandler<Result> {

	private final ActualAppendable appendable;

	private final Indentation indentation;

	private int depth;

	private boolean indented = true;

	private boolean empty;

	/**
	 * Creates a new {@link AbstractAppendingJavaTextJsonHandler}.
	 * 
	 * @param appendable
	 *            The {@link Appendable} to be used.
	 * @param indentation
	 *            The {@link Indentation} to be used.
	 */
	public AbstractAppendingJavaTextJsonHandler(ActualAppendable appendable, Indentation indentation) {
		if (null == appendable) {
			throw new IllegalArgumentException("writer is null");
		}
		if (null == indentation) {
			throw new IllegalArgumentException("indentation is null");
		}
		this.appendable = appendable;
		this.indentation = indentation;
	}

	/**
	 * Returns the {@link Appendable} this
	 * {@link AbstractAppendingJavaTextJsonHandler} has been created with.
	 * 
	 * @return The {@link Appendable} this
	 *         {@link AbstractAppendingJavaTextJsonHandler} has been created
	 *         with.
	 */
	protected ActualAppendable getAppendable() {
		return appendable;
	}

	@Override
	public final void onDocumentBegin() {
	}

	@Override
	public final void onDocumentEnd() {
	}

	@Override
	public final void onArrayBegin() throws JsonException {
		writeIndentation();
		append("[");
		depth++;
		empty = true;
	}

	@Override
	public final void onArrayEnd() throws JsonException {
		depth--;
		if (!empty) {
			writeIndentation();
		}
		empty = false;
		append("]");
	}

	@Override
	public final void onObjectBegin() throws JsonException {
		writeIndentation();
		append("{");
		depth++;
		empty = true;
	}

	@Override
	public final void onObjectEnd() throws JsonException {
		depth--;
		if (!empty) {
			writeIndentation();
		}
		empty = false;
		append("}");
	}

	@Override
	public final void onName(String name) throws JsonIndexException, JsonException {
		checkName(name);
		append(indentation.get(depth, true));
		indented = true;
		append(name);
		append("=");
	}

	@Override
	public final void onNext() throws JsonException {
		if ("".equals(indentation.getLineBreak())) {
			append(", ");
		} else {
			append(",");
		}
	}

	@Override
	public final void onNull() throws JsonException {
		writeIndentation();
		append("null");
	}

	@Override
	public final void onBoolean(boolean value) throws JsonException {
		writeIndentation();
		append(value ? "true" : "false");
	}

	@Override
	public final void onLong(long value) throws JsonException {
		writeIndentation();
		append(Long.toString(value));
	}

	@Override
	public final void onDouble(double value) throws JsonValueException, JsonException {
		checkDouble(value);
		writeIndentation();
		append(Double.toString(value));
	}

	@Override
	public final void onString(String value) throws JsonValueException, JsonException {
		checkString(value);
		writeIndentation();
		append(value);
	}

	private final void writeIndentation() throws JsonException {
		if (!indented) {
			append(indentation.get(depth, true));
		}
		indented = false;
		empty = false;
	}

	private final void append(String string) throws JsonException {
		try {
			appendable.append(string);
		} catch (IOException e) {
			throw new JsonHandlingException(e);
		}
	}

}
