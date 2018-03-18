package com.amazonaws.cloudhsm.examples.mutualauth.operations;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.amazonaws.cloudhsm.examples.mutualauth.pojo.Arguments;

public class CLIOptions {

	private static final Logger log = Logger.getLogger(CLIOptions.class.getName());
	private String[] args = null;
	private Options options = new Options();

	public CLIOptions(String[] args) {

		this.args = args;

		Option alias = Option.builder()
				.required(true)
				.longOpt("key-alias")
				.hasArg()
				.desc("This option allows you to set an alias for your KMS Key.")
				.build();

		Option mutualAuthUrl = Option.builder()
				.required(true)
				.longOpt("url")
				.hasArg()
				.desc("This option allows you to set an alias for your KMS Key.")
				.build();

		Option certFilePath = Option.builder()
				.required(true)
				.longOpt("cert-file")
				.hasArg()
				.desc("This option allows you to set an alias for your KMS Key.")
				.build();

		Option hsmCryptoUser = Option.builder()
				.required(false)
				.longOpt("hsm-user-name")
				.hasArg()
				.desc("HSM CU User Username. If you're not providing credentials using CLI, pass it using env variables or system properties. If just username is passed and not password via options, password is to be entered via prompt.")
				.build();

		Option hsmCryptoPassword = Option.builder()
				.required(false)
				.longOpt("hsm-user-password")
				.hasArg()
				.desc("HSM CU User Username. If you're not providing credentials using CLI, pass it using env variables or system properties. If just username is passed and not password via options, password is to be entered via prompt.")
				.build();

		Option verify = Option.builder()
				.required(false)
				.longOpt("verify")
				.desc("This option is used to verify if the key and certificate are a match.")
				.build();
		
		options.addOption(alias);
		options.addOption(mutualAuthUrl);
		options.addOption(certFilePath);
		options.addOption(hsmCryptoUser);
		options.addOption(hsmCryptoPassword);
		options.addOption(verify);
	}

	public Arguments parse() {
		Arguments cliArgs = new Arguments();
		CommandLineParser parser = new DefaultParser();

		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);

			if (cmd.hasOption("h"))
				help();

			if (cmd.hasOption("key-alias")) {
				String hsmKeyAlias = cmd.getOptionValue("key-alias");
				cliArgs.setKeyAlias(hsmKeyAlias);
			}
			if (cmd.hasOption("url")) {
				String mutualAuthUrl = cmd.getOptionValue("url");
				cliArgs.setMutualAuthUrl(mutualAuthUrl);
			}
			if (cmd.hasOption("cert-file")) {
				String certificateFile = cmd.getOptionValue("cert-file");
				cliArgs.setCertificateFile(certificateFile);
			}
			if (cmd.hasOption("hsm-user-name")) {
				String hsmCryptoUser = cmd.getOptionValue("hsm-user-name");
				cliArgs.setHsmCryptoUser(hsmCryptoUser);
			}
			if (cmd.hasOption("hsm-user-password")) {
				String hsmCryptoPassword = cmd.getOptionValue("hsm-user-password");
				cliArgs.setHsmCryptoPassword(hsmCryptoPassword);
			}
			if (cmd.hasOption("verify")) {
				cliArgs.setVerify(true);
			}

		} catch (ParseException e) {
			log.log(Level.SEVERE, "Failed to parse comand line properties", e);
			help();
		} 
		return cliArgs;
	}

	private void help() {
		// This prints out some help
		HelpFormatter formater = new HelpFormatter();

		formater.printHelp("MutualAuthRunner", options);
		System.exit(0);
	}
}
