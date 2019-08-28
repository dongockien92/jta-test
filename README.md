# jta-test
XA Transactions test with spring boot 2.1 and atomikos

# Note:

_ Rollback completed with postgresql 11.2 and mysql 8.0.17 + 5.7.27 but it didn't completed with mysql 5.7.24. Maybe that error has resolved at version [5.7.25](https://dev.mysql.com/doc/relnotes/mysql/5.7/en/news-5-7-25.html)

_ Spring boot 2.1.7 is using atomikos 4.0.6. Replacing atomikos 5.0.1 will be error for postgresql
