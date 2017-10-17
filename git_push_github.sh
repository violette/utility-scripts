#!/bin/bash
# Use to synchronize your local repository with upstream changes.
# See https://www.atlassian.com/git/tutorials/syncing/git-pull
# by vrom 2016-2017
# First step is to add the remote, call it "upstream":
# then: git remote add upstream https://github.com/whoever/whatever.git
# remote is not sync with upstream (original github)
# once rebase is performed, you have to push changes to your remote
current_branch=$(git symbolic-ref --short HEAD)
# This above, save your current branch name
echo -e "------------------------------"
echo -e " >>>> REBASING YOUR WORK <<<<"
echo -e "------------------------------"
git checkout master
git fetch upstream
echo -e " \n>> git pull on master... "
git rebase upstream/master
git pull --rebase origin master
# git pull --rebase origin master
echo -e " \n>>"&&git checkout $current_branch&&echo -e ">>"
echo -e " \n>> Rebasing $current_branch and replay your work on top of master "
git rebase master
echo -e "------------------------------"
echo -e " >>>>UPDATE YOUR REMOTE!  >>>>"
echo -e " >>> git push -f"
echo -e "------------------------------"
