create index IX_729869EE on OpenSocial_Gadget (companyId);
create unique index IX_A6A89EB1 on OpenSocial_Gadget (companyId, url);
create index IX_E1F8627A on OpenSocial_Gadget (uuid_);

create index IX_8DA87686 on OpenSocial_OAuthConsumer (moduleId);
create index IX_ECFEE4A on OpenSocial_OAuthConsumer (moduleId, serviceName);

create index IX_7912E165 on OpenSocial_OAuthToken (moduleId, serviceName);
create index IX_BE330A09 on OpenSocial_OAuthToken (userId, moduleId, serviceName, tokenName);