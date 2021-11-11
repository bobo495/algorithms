#!/bin/bash

# ------------------------------------------------------------------
# 193. 有效电话号码
#
# https://leetcode-cn.com/problems/valid-phone-phone_numbers
#
# 给定一个包含电话号码列表（一行一个电话号码）的文本文件 file.txt，写一个单行 bash 脚本输出所有有效的电话号码。
# 你可以假设一个有效的电话号码必须满足以下两种格式： (xxx) xxx-xxxx 或xxx-xxx-xxxx。（x 表示一个数字）
# 你也可以假设每行前后没有多余的空格字符。
#
# @author yibo
#
# @date 2021-11-10 17:25
# ------------------------------------------------------------------

grep -E '^([0-9]{3}-|\([0-9]{3}\) )[0-9]{3}-[0-9]{4}$' file.txt


#function check_phone_number() {
#  phone_number=$1
#  if [ "${#phone_number}" = 12 ] && [ "${phone_number:3:1}" == "-" ] && [ "${phone_number:7:1}" == "-" ]; then
#    number="${phone_number:0:3}${phone_number:4:3}${phone_number:8:4}"
#    if [ "$(echo "$number" | awk '{print($0~/^[-]?([0-9])+[.]?([0-9])+$/)}')" == 1 ]; then
#      echo "$phone_number"
#    fi
#  fi
#
#  if [ "${#phone_number}" = 14 ] && [ "${phone_number:0:1}" == "(" ] && [ "${phone_number:4:2}" == ") " ] && [ "${phone_number:9:1}" == "-" ]; then
#    number="${phone_number:1:3}${phone_number:6:3}${phone_number:10:4}"
#    if [ "$(echo "$number" | awk '{print($0~/^[-]?([0-9])+[.]?([0-9])+$/)}')" == 1 ]; then
#      echo "$phone_number"
#    fi
#  fi
#}
#
#while read line || [[ -n ${line} ]]; do
#  check_phone_number "$line"
#done <file.txt
