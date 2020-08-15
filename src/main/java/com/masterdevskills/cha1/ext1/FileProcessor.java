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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

/**
 * TODO : Write a file processor that would read text from a text file.
 * Discard the empty line
 * Log the count of the lines of the file
 * Write in another file
 */

public class FileProcessor {
    private static final Logger logger = Logger.getLogger(FileProcessor.class.getName());

    private static boolean isEmptyLine(String s) {
        return s == null || "".equals(s);
    }

    /**
     * Add your code in the following method
     * This method is supposed to read text from given file
     * and return the all the lines as a List of string
     *
     * @param fileName the filename
     *                 <p>
     *                 hints:
     * @see List#removeIf(Predicate)
     */
    public List<String> readFileFrom(String fileName) { //better name fileUri
        try {
            var lines =  Files.readAllLines(Paths.get(fileName));
            lines.removeIf(FileProcessor::isEmptyLine);
            logger.info("Number of lines in file : " + lines.size());

            return lines;
        } catch (Exception ex) {
            logger.warning("File not found");

            return new ArrayList<>();
        }
    }

    /**
     * TODO: Implement this method that takes a list of string and write in a file
     *
     * @param lines    list of string
     * @param fileName fileName to write
     *                 <p>
     *                 hints
     * @see String#join(CharSequence, CharSequence...)
     */
    public void writeToFile(List<String> lines, String fileName) { //better name fileUri
        var filePath =  Paths.get(fileName);
        if(!Files.exists(Paths.get(fileName))) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                logger.warning("Can not create the File");
            }
        }

        lines.removeIf(FileProcessor::isEmptyLine);
        logger.info("Number of lines in file : " + lines.size());

        try {
            Files.write(filePath, lines);
        } catch (IOException e) {
            logger.warning("Can not write in File");
        }
    }
}

