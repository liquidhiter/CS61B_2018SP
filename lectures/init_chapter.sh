#!/usr/bin/env bash
# init_chapter.sh
#
# Copyright 2023 Maxin
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.

new_chapter="$1"
# ${new_chapter^} is supported only in bash ver 4.0+
first_char=${new_chapter:0:1}
first_char=$(echo $first_char | tr 'a-z' 'A-Z')
header="# CS61B 2018 Spring Learning Notes - $first_char${new_chapter:1}"

#!CONVENTION: new chapter name is separated by whitespaces
dlim=" "
new_dlim="_"
new_chapter="${new_chapter/$dlim/$new_dlim}"
echo "Initializing $new_chapter"

# Create the top-level directory
mkdir "$new_chapter"

# Create code sub-directory
code_dir="$new_chapter"/"code"
mkdir $code_dir

# Create notes sub-directory
notes_dir="$new_chapter"/"notes"
mkdir $notes_dir

# Create notes markdown file
note_md="$new_chapter".md
# touch "$notes_dir"/$note_md
echo "$header" > "$notes_dir/$note_md"