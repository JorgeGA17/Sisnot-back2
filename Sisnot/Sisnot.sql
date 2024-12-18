PGDMP  ,        	    
    
    |            Sisnot    16.4    16.4 )    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17146    Sisnot    DATABASE     {   CREATE DATABASE "Sisnot" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE "Sisnot";
                postgres    false            �            1259    17147    alumno_cursos    TABLE     c   CREATE TABLE public.alumno_cursos (
    alumno_fk bigint NOT NULL,
    curso_fk bigint NOT NULL
);
 !   DROP TABLE public.alumno_cursos;
       public         heap    postgres    false            �            1259    17151    alumnos    TABLE     �  CREATE TABLE public.alumnos (
    id bigint NOT NULL,
    apellido_materno character varying(100),
    apellido_paterno character varying(100),
    celular character varying(255),
    direccion character varying(100),
    dni character varying(255),
    email character varying(100),
    estado character varying(20),
    fecha_ingreso timestamp(6) without time zone,
    nombre character varying(100)
);
    DROP TABLE public.alumnos;
       public         heap    postgres    false            �            1259    17150    alumnos_id_seq    SEQUENCE     �   ALTER TABLE public.alumnos ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.alumnos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    17158    curso_docentes    TABLE     e   CREATE TABLE public.curso_docentes (
    docente_fk bigint NOT NULL,
    curso_fk bigint NOT NULL
);
 "   DROP TABLE public.curso_docentes;
       public         heap    postgres    false            �            1259    17162    cursos    TABLE     �   CREATE TABLE public.cursos (
    id bigint NOT NULL,
    ciclo character varying(30),
    credito character varying(30),
    estado character varying(30),
    nom_curso character varying(100)
);
    DROP TABLE public.cursos;
       public         heap    postgres    false            �            1259    17161    cursos_id_seq    SEQUENCE     �   ALTER TABLE public.cursos ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.cursos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    220            �            1259    17168    docentes    TABLE     d  CREATE TABLE public.docentes (
    id bigint NOT NULL,
    apellido_materno character varying(100),
    apellido_paterno character varying(100),
    celular character varying(255),
    direccion character varying(100),
    dni character varying(255),
    email character varying(100),
    estado character varying(30),
    nombre character varying(100)
);
    DROP TABLE public.docentes;
       public         heap    postgres    false            �            1259    17167    docentes_id_seq    SEQUENCE     �   ALTER TABLE public.docentes ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.docentes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    222            �            1259    17176    notas    TABLE     �  CREATE TABLE public.notas (
    id bigint NOT NULL,
    componente1_nota double precision,
    componente2_nota double precision,
    componente3_nota double precision,
    componente4_nota double precision,
    fecha_registro timestamp(6) without time zone,
    nota_final double precision,
    nota_parcial double precision,
    promedio_final double precision,
    alumno_fk bigint,
    curso_fk bigint
);
    DROP TABLE public.notas;
       public         heap    postgres    false            �            1259    17175    notas_id_seq    SEQUENCE     �   ALTER TABLE public.notas ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.notas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    224            �          0    17147    alumno_cursos 
   TABLE DATA           <   COPY public.alumno_cursos (alumno_fk, curso_fk) FROM stdin;
    public          postgres    false    215   �2       �          0    17151    alumnos 
   TABLE DATA           �   COPY public.alumnos (id, apellido_materno, apellido_paterno, celular, direccion, dni, email, estado, fecha_ingreso, nombre) FROM stdin;
    public          postgres    false    217   �2       �          0    17158    curso_docentes 
   TABLE DATA           >   COPY public.curso_docentes (docente_fk, curso_fk) FROM stdin;
    public          postgres    false    218   �5       �          0    17162    cursos 
   TABLE DATA           G   COPY public.cursos (id, ciclo, credito, estado, nom_curso) FROM stdin;
    public          postgres    false    220   �6       �          0    17168    docentes 
   TABLE DATA           z   COPY public.docentes (id, apellido_materno, apellido_paterno, celular, direccion, dni, email, estado, nombre) FROM stdin;
    public          postgres    false    222   �A       �          0    17176    notas 
   TABLE DATA           �   COPY public.notas (id, componente1_nota, componente2_nota, componente3_nota, componente4_nota, fecha_registro, nota_final, nota_parcial, promedio_final, alumno_fk, curso_fk) FROM stdin;
    public          postgres    false    224   �E       �           0    0    alumnos_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.alumnos_id_seq', 10, true);
          public          postgres    false    216            �           0    0    cursos_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.cursos_id_seq', 234, true);
          public          postgres    false    219            �           0    0    docentes_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.docentes_id_seq', 14, true);
          public          postgres    false    221            �           0    0    notas_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.notas_id_seq', 5, true);
          public          postgres    false    223            2           2606    17157    alumnos alumnos_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.alumnos
    ADD CONSTRAINT alumnos_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.alumnos DROP CONSTRAINT alumnos_pkey;
       public            postgres    false    217            :           2606    17166    cursos cursos_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.cursos
    ADD CONSTRAINT cursos_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.cursos DROP CONSTRAINT cursos_pkey;
       public            postgres    false    220            >           2606    17174    docentes docentes_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.docentes
    ADD CONSTRAINT docentes_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.docentes DROP CONSTRAINT docentes_pkey;
       public            postgres    false    222            F           2606    17180    notas notas_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.notas
    ADD CONSTRAINT notas_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.notas DROP CONSTRAINT notas_pkey;
       public            postgres    false    224            @           2606    17194 $   docentes uk142vxdo74spxweh5uf4ew55et 
   CONSTRAINT     `   ALTER TABLE ONLY public.docentes
    ADD CONSTRAINT uk142vxdo74spxweh5uf4ew55et UNIQUE (email);
 N   ALTER TABLE ONLY public.docentes DROP CONSTRAINT uk142vxdo74spxweh5uf4ew55et;
       public            postgres    false    222            B           2606    17192 $   docentes uk78x8vnxmav5c9mbkmtd66cusv 
   CONSTRAINT     ^   ALTER TABLE ONLY public.docentes
    ADD CONSTRAINT uk78x8vnxmav5c9mbkmtd66cusv UNIQUE (dni);
 N   ALTER TABLE ONLY public.docentes DROP CONSTRAINT uk78x8vnxmav5c9mbkmtd66cusv;
       public            postgres    false    222            D           2606    17190 $   docentes uk9m69xfxkiai0fklollxd0buqi 
   CONSTRAINT     b   ALTER TABLE ONLY public.docentes
    ADD CONSTRAINT uk9m69xfxkiai0fklollxd0buqi UNIQUE (celular);
 N   ALTER TABLE ONLY public.docentes DROP CONSTRAINT uk9m69xfxkiai0fklollxd0buqi;
       public            postgres    false    222            4           2606    17184 #   alumnos ukf2c9i14wnebiikl4lldh5yam2 
   CONSTRAINT     ]   ALTER TABLE ONLY public.alumnos
    ADD CONSTRAINT ukf2c9i14wnebiikl4lldh5yam2 UNIQUE (dni);
 M   ALTER TABLE ONLY public.alumnos DROP CONSTRAINT ukf2c9i14wnebiikl4lldh5yam2;
       public            postgres    false    217            6           2606    17182 #   alumnos ukl48mpyy32xjxat2do2qypsx5v 
   CONSTRAINT     a   ALTER TABLE ONLY public.alumnos
    ADD CONSTRAINT ukl48mpyy32xjxat2do2qypsx5v UNIQUE (celular);
 M   ALTER TABLE ONLY public.alumnos DROP CONSTRAINT ukl48mpyy32xjxat2do2qypsx5v;
       public            postgres    false    217            8           2606    17186 #   alumnos ukrrq96s55g45kywh0ypeed2u1v 
   CONSTRAINT     _   ALTER TABLE ONLY public.alumnos
    ADD CONSTRAINT ukrrq96s55g45kywh0ypeed2u1v UNIQUE (email);
 M   ALTER TABLE ONLY public.alumnos DROP CONSTRAINT ukrrq96s55g45kywh0ypeed2u1v;
       public            postgres    false    217            <           2606    17188 "   cursos ukstj3gt43hxrcafnkqb0mco56j 
   CONSTRAINT     b   ALTER TABLE ONLY public.cursos
    ADD CONSTRAINT ukstj3gt43hxrcafnkqb0mco56j UNIQUE (nom_curso);
 L   ALTER TABLE ONLY public.cursos DROP CONSTRAINT ukstj3gt43hxrcafnkqb0mco56j;
       public            postgres    false    220            G           2606    17200 !   alumno_cursos fk_alumcur_alumnoid    FK CONSTRAINT     �   ALTER TABLE ONLY public.alumno_cursos
    ADD CONSTRAINT fk_alumcur_alumnoid FOREIGN KEY (alumno_fk) REFERENCES public.alumnos(id);
 K   ALTER TABLE ONLY public.alumno_cursos DROP CONSTRAINT fk_alumcur_alumnoid;
       public          postgres    false    217    4658    215            H           2606    17195     alumno_cursos fk_alumcur_cursoid    FK CONSTRAINT     �   ALTER TABLE ONLY public.alumno_cursos
    ADD CONSTRAINT fk_alumcur_cursoid FOREIGN KEY (curso_fk) REFERENCES public.cursos(id);
 J   ALTER TABLE ONLY public.alumno_cursos DROP CONSTRAINT fk_alumcur_cursoid;
       public          postgres    false    215    4666    220            I           2606    17205 "   curso_docentes fk_cursodoc_cursoid    FK CONSTRAINT     �   ALTER TABLE ONLY public.curso_docentes
    ADD CONSTRAINT fk_cursodoc_cursoid FOREIGN KEY (curso_fk) REFERENCES public.cursos(id);
 L   ALTER TABLE ONLY public.curso_docentes DROP CONSTRAINT fk_cursodoc_cursoid;
       public          postgres    false    220    218    4666            J           2606    17210 $   curso_docentes fk_cursodoc_docenteid    FK CONSTRAINT     �   ALTER TABLE ONLY public.curso_docentes
    ADD CONSTRAINT fk_cursodoc_docenteid FOREIGN KEY (docente_fk) REFERENCES public.docentes(id);
 N   ALTER TABLE ONLY public.curso_docentes DROP CONSTRAINT fk_cursodoc_docenteid;
       public          postgres    false    218    222    4670            K           2606    17215    notas fk_nota_alumnoid    FK CONSTRAINT     y   ALTER TABLE ONLY public.notas
    ADD CONSTRAINT fk_nota_alumnoid FOREIGN KEY (alumno_fk) REFERENCES public.alumnos(id);
 @   ALTER TABLE ONLY public.notas DROP CONSTRAINT fk_nota_alumnoid;
       public          postgres    false    4658    224    217            L           2606    17220    notas fk_nota_cursoid    FK CONSTRAINT     v   ALTER TABLE ONLY public.notas
    ADD CONSTRAINT fk_nota_cursoid FOREIGN KEY (curso_fk) REFERENCES public.cursos(id);
 ?   ALTER TABLE ONLY public.notas DROP CONSTRAINT fk_nota_cursoid;
       public          postgres    false    4666    220    224            �   4   x�Ȼ  �К7�G����"��[�-���0��a1a������$}b	�      �     x���[��F���WQR��\7W���6%0�4���n��N�E�"˘u��rLO��ғQK%�A�||�LaZdv��$��(����I0kR��,�}Z�_��`2Z��k��������ꣿoO`�e�.�L�(	���ƌ�ZQ��Wx�ؔiQy��'ߛ�2&��J ���3-�%Υ�F $�2¹����n��nV,3��~\do�tL�X����^�nH����`�oq�O��0d��"���ԏ�d�9����)>��2����}�<���m�nw�������G1E�`|;Q&�y�<��'�V�dmSX�tb�P��;R�A��n&*���R����q��/��/���l�"��UȩP�+�֓�rbr(M�D�3����(�_��]G��x<|nQE0(�Z#M�Nuw�k����I�}DZ
x� ٘l���!-��f�`$ӡ���ɤ���V�w���g�5��¹�V��`���,�e�~�=P�*>|���+a����PT��>�$u����{t�E(��!��������t�19n �i�5�v9�4|��Lj6�S�0@57�j�"R�"v-w�;?;b��l5C[MW}A_t'��r��yQ&�A���"(�I�L��TK���I�(�*v�'������m�����6w&���]�� �T�C���z2<�\Z�!�̒��h �-r|�%q��@&�V�pW$�x �Y�q�`?������ܷ>��*Tl+S�����}�=���-`�      �   �   x��˕�0�5.f
�^^�u���Z��8	�:a����PD�|�k����� p���l��%5�s�^@5�p��#�����	\5v��Zi1*�%�^d4f�:yA E����g��N�[�:�!_��%o,7�o�̿��:�l��.�ۥ���/�#�v�|*�|�;��:#��Y���9��u9�      �   	  x��Z�r��ů`h'��1$4�5IpR�r9�sÆ(���#���|�1��uˎV��~�~p���)��^����蔝U+b%O	;���Qt��$�N�(f=\�D�?+�t�/�ۗe�Q��8�d�h��ꕶQFפ��ygoe���@�{���Q�r�o��m U/r�F�Q��c1˱U�_c��x�(9θ[Z:��q��g��lGZ�ݠFX�/杜(+8s����=������g�� �FYu�7-��v������$j��ן��6��nRfe�clE�tQ]X��=�(�Hj�,z�6�N:�gsR"�kn�d�I����Y�(��[P�z��#u�0
�H��bW������~�;͟��%�V�A�[�@|�p6�[Zo)����Q^s}����<�OF���2J��7��ˋ���1��d'�e���Pp�&�s(~8k
�"���t�����-e�󋤷Y�z�uqq����7ig�e1�]N2v�mQA�9X�S�핋��ph��������\<i�E�K���U�z�~8���3"|E��U�F�N@�L�����2�|X�k5�k��Q�pޛlWQy��J�oK7��	'ٓ�)7���\�㕙��<S<Ee��G�9G��"�\6Fe�rR�6Fe�i�� #�w��2*_��l����@Z��2p�
=1bt!M�ԃBCx�n1���X�Qu

�WiZ\�HqW�x ���\q^��������*�״���R��H���	�	���,e�
V9���v3{B`:��&��Vz���Hvxz�M��IQ���bP�qP��*��^k	dW�U57��*�6��u��$8���`.z����S��\˱���K���*���=w��dE�:� �S��5��1����W�����z�§tL7P=->O�Х�4k��j�%G�b+�z\%�[ ��@�ԣ���L��6�-e�Eu��n��5�cJI�ݿ��#Ӌ��1\\�8�C�cmp�<eܫv�V��6Fu.���b�JȈ�������m'��^�I�V��zDVK�Ehդ�Dn�EM��N���B�T�P\]Vs9_ߞɷM�y WQSp�y�EM\x�:GM�����5d��G���JN�p}���l�ݿ�%�W	���%���>�ޔKd+�����	����8�F�p�ֲ�&�A���o�w^�_�We	�oG�3��r����z����Qz��&s��2��[}��G��y!���a��ѩM-��7x59Ձ���b��v���)p�Dm�Er
M؎��t@r�N���߰A�+
�l8��i��B�ih�� ��Z-���L���E�/b�$A��v{k�:4B���t��\�1�	�rԵ��А$%#2�����G���z����cp�Hv ��g�ޒ9V{���ֶz"4I��s���גC�����.�5M�i�֔-�4�͎o:�$�9(�@�$Kߟ+�;@�����l��+�ÜK8����^u�`��"x�ѯ@g�PAYr�E_pRqMnrb��/θ�>~[�ul͑�RV�m�A�p�l���ܟ���R�<46��;���@��$��}�o:֎�γ�%���
�\�V-����8_��'9��a,9��h��3��JC^q*#�C=0�C<�Ʌ��yF*�:������9oB�:����G�Ҽ⊑e�8ȃ(x<���_I�M������WIͻ�E��N�^�l�o�1:L�=M�<��o�f?)wOT�ٚ�����|�qС{�n�K��ƺ.��H(�`9�Ԓ.O��J�<e���kJ� "P<�W�&l!�R��B�Bt�� R�h�Z��<�Ԋ��퐵L~��c��M�d�"̥�P�Ђ�%�aMJR���h�	�1��f֥�K`p���~�A��@����]g�`�/�����ND��2��gZ(PW`�g$���5��L���J���NLʵٶ��&j��?H;�qSr��� ���˕a�G������[�B�*�2�Uh�iے	ޅ$�_�� 8$T\���
�X� 1'�""��0^Bn2���k	��!�#U�>m�W����  ����J5&/!3t88�pԁ�Ѥ>3�[���wy�����ԁ�?�nс=���� u��]��C�D!�����fK� [����b���n�
r�U[��I�n8��E���b������z�b����(}�H���{A`-}�ޥ`��/�4�� ������p�@�6M'�f-,7+�\����ZVȥM�VP�o6>$M��ɪ6��1~�5Hb�Y��'���;I�
PSs];I[�mi��X�ʺn�m}_p۟�	 ���虧���S���1T�V�"ᚇ�S�8}*��'����쩼3]*)P�\�YiVqZ<����\��#=��l��Wu_��ƨn��T��TEz\�i��X�,���4�"�$'���Ca���y�8�~\�&�y}</-鑤��»H�d8X��A�&9ׅ�^��@��Ӹt��-�qɏi�VK_}�T��Kٮ��4�����x�Z/�$�YH��k�4���S�>���o��'��[���y�X�MT�W���</����h�S/Fx!M�'I�I
�$��l��A��Z��f����cJJ�0G�+�LZ��<��O:{1��@pԟ嘤�����6�e�� �J�vM���Yv�������Q�M� ����YY0�<�����,��_u�<���g(u���W��� D�~8r�J������ˏ��SE��@'�      �   �  x��T�n�8�6Oai������W�Rg��Bi��"o¶d	���y�y�y���L�jӛ	�����H�.u$���b$R
ƥ$��O�4���2)"�b�l�k����U:�2'�l�������I^V@�rY��6ݓXIƄ䌬-�MOsc���KD��
9�����b��?��/��P �*+k/$k�O#�lt�dH��e!I��X�3]ל�_)4�����?}�Z��q���
r!�h�73����p6ְ��e���b�Ve����u��d��a	d���P �_cB��{C��ȁ/ha&3�
�d�Z(�hA��l��yCx�[�9T��kء^O�ǲ����E�� �����ǋ8�$���xpx.��>IM�:O���d�,�
�D��2��ugp��ͶGC��"��A�x���m?�ތh>%G��i��=�ƗJ���Plʚf[�+Jo�)��䢸��*E�,�LU>�+��a@�P�2!9���7��3����N��b��]l�b��+L���s�IF��oO�&߿}t��2%�W>�o3���8nU��Ǵ��5<��c�V@vP-]���(b,࿘j�Z�����f|oƞL׌�I���N�m�����&MK��BpwY,��\�S�{(9'���CD���;��e�] �i��\a}�eI�-[��F�m��2����	WJ�BK�
�>�Wc�@9WD�n(�B��Gs`q��ӶB�-�fz��n=�c����4s��U�*��=i�Z�W7�f��R�J<�0&c[�2���O��ك[�2�Q�X�a�>��<�ϖ4sI�p��r�]�X�6�AE;�l���u�D+@��L��Xʯ8	sl���0�?�0�T�Gl��R�ϙ(��O����m��F3��@�O�EmK���=������      �   �   x�e��1E�PE��ص��:�%���,�������|��/���Krsl+�J����$�c�{�a#���6�)�Z&�f��ڗ h��c�O�ښ�5���MJ���'�d�~*�g�I���,ok�=�C�\'�7ǳ��f#�������0�`�oB�M64     