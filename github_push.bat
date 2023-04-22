#!/bin/bash

@echo off

setlocal enableextensions

:: Get current date and time
for /f "tokens=2 delims==" %%G in ('wmic os get localdatetime /value') do set "dt=%%G"
set "YYYY=%dt:~0,4%"
set "MM=%dt:~4,2%"
set "DD=%dt:~6,2%"
set "HH=%dt:~8,2%"
set "MIN=%dt:~10,2%"
set "SEC=%dt:~12,2%"

set "timestamp=%YYYY%%MM%%DD%_%HH%%MIN%%SEC%"

cd /d "C:/Users/darkn/Downloads/GISTDA/Spring/cspp"

git add .
git commit -m "commit at %timestamp%"
git push origin main