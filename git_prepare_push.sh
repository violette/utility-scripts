#!/bin/bash
# Use to synchronize your local repository with upstream changes
# See https://www.atlassian.com/git/tutorials/syncing/git-pull
# by vrom 2015-2020
current_branch=$(git symbolic-ref --short HEAD)
# This above, save your current branch name
echo -e "------------------------------"
echo -e " >>>> REBASING YOUR WORK <<<<"
echo -e "------------------------------"
git checkout master
echo -e " \n>> git pull on master... "
git pull --rebase origin master
echo -e " \n>>"&&git checkout $current_branch&&echo -e ">>"
echo -e " \n>> Rebasing $current_branch and replay your work on top of master "
git rebase master
echo -e "------------------------------"
echo -e " >>>>       READY!       >>>>"
echo -e "------------------------------"
