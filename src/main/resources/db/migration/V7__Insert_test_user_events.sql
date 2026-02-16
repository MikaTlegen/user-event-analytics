INSERT INTO user_events_entity (user_id, event_type, event_data, details) VALUES
                                                                              ('2', 'LOGIN', NOW(), '{}'),
                                                                              ('2', 'PAGE_VIEW', NOW() - INTERVAL '10 minutes', '{"page":"home"}'),
                                                                              ('3', 'LOGIN', NOW() - INTERVAL '1 hour', '{}'),
                                                                              ('3', 'PURCHASE', NOW() - INTERVAL '30 minutes', '{"item":"laptop","price":1200}'),
                                                                              ('4', 'LOGIN', NOW() - INTERVAL '2 hours', '{}'),
                                                                              ('4', 'LOGOUT', NOW() - INTERVAL '1 hour', '{}'),
                                                                              ('6', 'LOGIN', NOW() - INTERVAL '3 hours', '{}'),
                                                                              ('6', 'PAGE_VIEW', NOW() - INTERVAL '2 hours', '{"page":"products"}'),
                                                                              ('6', 'PURCHASE', NOW() - INTERVAL '1 hour', '{"item":"mouse","price":25}');